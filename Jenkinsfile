# 由于这边是单机版Jenkins，所以直接使用master节点：
node('master') {
#加入一些环境变量，供下面调用，也通用，只需改变本变量即可自动部署，
# APP： 项目打包后的名称，你mvn clean package一下，生成的jar包全名就是它的值。
# APP_NAME: 部署到K8s中的工作负载名称，以及在镜像仓库中的仓库名称
# START_COMMOND: 是Dockerfile添加的CMD命令,当容器被启动时会被执行（这里是java应用，故为此例，springboot的一些命令都可以加到此，比如-DNACOS_SERVER -DNACOS_NAMESPACE --server.port 等等）。
    withEnv(['APP=msg-1.0.jar','APP_NAME=msg', 'START_COMMOND="CMD java -Xms512M -Xmx2048M -jar app.jar --server.port=8081  -Dclient.logRoot=/app/logs"']) {
    # 这是一个流水线的stage， 帮助在blueOcean中部署项目时，选择诸如部署的环境，部署项目的配置文件的active等
        stage('EnvSelect') {
            echo "0.EnvSelect Stage"
                script {
                    env.NAME_SPACE = input message: 'Which Env To Deploy', ok: 'YES', parameters: [choice(name: 'NAME_SPACE', choices: ['k8snb', 'k8stest-blueocean'], description: 'Which Env To Deploy')]
                    
                    env.PROFILE = input message: 'Which Profile To Active', ok: 'YES', parameters: [string(defaultValue: env.NAME_SPACE, description: 'Which Profile To Active', name: 'PROFILE', trim: false)]
                 }
        }
        # 顾名思义，克隆项目
        stage('Clone') {
            echo "1.Clone Stage"
            sh "date"
            checkout scm
            script {
                build_tag = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
                if (env.BRANCH_NAME != 'master') {
                    build_tag = "${env.BRANCH_NAME}-${build_tag}"
                }
            }
        }
        # 顾名思义，用maven项目，这边加了jenkins部署主机的maven全路径执行clean package 并跳过测试
        stage('Compile') {
            echo "2.Compile Stage"
            sh "/usr/local/apache-maven-3.8.2/bin/mvn clean package -DskipTests"
            sh "mv target/$APP ."
        }
        # 配置Dockerfile，将通用的Dockerfile 复制到工作目录，然后sed替换里面的<APP>变量。
        stage('Config') {
            echo "3.Config Stage"
            # 这里pwd其实就是jenkins的工作空间目录
            sh "pwd" 
            sh "cp -r /usr/local/script/Dockerfile ./Dockerfile"
            sh "sed -i 's/<APP>/$APP/' Dockerfile"
        }
        # 构建镜像，打tag，上传镜像仓库，我这边用的hub.docker.com测试，故弄的公开镜像，生产中得搭建私有镜像仓库，或者使用阿里云、华为云等的组件（花钱买说白了就是）
        #以下4个sh分别表示1、登录镜像仓库；2、构建本地镜像；3、推送镜像到远程镜像仓库；4、推送完删除本地镜像（可以去掉本命令不自动删，自己手动删也是也一样的，但一般本地不留存，因为远程镜像仓库都有了）；
        stage('Build') {
            echo "4.Build Stage"
            withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'dockerhubPassword', usernameVariable: 'dockerhubUser')]) {
                sh "sudo docker login -u ${dockerhubUser} -p ${dockerhubPassword}  docker.io"
                sh "sudo docker build -t  docker.io/mydockerhubname/$APP_NAME:${build_tag} ."
                sh "sudo docker push  docker.io/mydockerhubname/$APP_NAME:${build_tag}"
                sh "sudo docker rmi mydockerhubname/$APP_NAME:${build_tag}"
            }
        }
        # 部署镜像到k8s容器中：这里也是复制通用的k8s.yaml文件，替换其中的3个变量，并执行。最后输出查看svc的命令
        # 这里我补充一下：这里是我部署k8s的命令，并不适合所有人。如果大家是自己玩，比如启动jar的方式启动程序的话。可以将下面6个sh命令换一下即可，比如写个.sh脚本（里面写jar的启动方式，启动之前替换下端口号），然后再在这边执行一下。再比如docker镜像的方式启动，那就直接docker run命令启动就可以了。
        # 这边的思路就是sed命令替换脚本里的内容，使得脚本变得通用了，都是linux上的一些命令，没什么出奇的地方。
        stage('Deploy') {
            echo "5. Deploy Stage"
            if (env.BRANCH_NAME == 'master') {
                input "确认要部署线上环境吗？"
            }
            sh "cp -r /usr/local/script/k8s.yaml ."
            sh "sed -i 's/<APP_NAME>/$APP_NAME/' k8s.yaml"
            sh "sed -i 's/<NAMESPACE>/$NAME_SPACE/' k8s.yaml"
            sh "sed -i 's/<APP_TAG>/${build_tag}/' k8s.yaml"
            sh "sudo kubectl apply -f k8s.yaml --record"
            sh "sudo kubectl get svc -A | grep $APP_NAME"
        }
    }
}
