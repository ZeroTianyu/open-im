create table oauth2_authorization_consent
(
    registered_client_id varchar(100)  not null,
    principal_name       varchar(200)  not null,
    authorities          varchar(1000) not null,
    primary key (registered_client_id, principal_name)
);

INSERT INTO oauth2_authorization_consent (registered_client_id, principal_name, authorities) VALUES ('1', 'admin', 'SCOPE_message.read');
INSERT INTO oauth2_authorization_consent (registered_client_id, principal_name, authorities) VALUES ('58ff6277-2709-422c-bf77-d2f0a92b3b38', 'admin', 'SCOPE_message.read');
