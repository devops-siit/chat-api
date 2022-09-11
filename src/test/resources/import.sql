
insert into account ( created_at, updated_at, uuid, name, username) values ( '2022-07-15 14:52:49.065758', '2022-07-15 14:52:49.065813', '7c20fb12-40d8-4322-ba33-9c05203868e9', 'Jane J', 'strawberry');

insert into account (created_at, updated_at, uuid, name, username) values ( '2022-07-15 14:52:49.065758', '2022-07-15 14:52:49.065813', '7c20fb12-50d8-4322-ba33-9c05203868e9','Claudia C', 'coco');

insert into account (created_at, updated_at, uuid, name, username) values ( '2022-07-15 14:52:49.065758', '2022-07-15 14:52:49.065813', '7c20fb12-60d8-4322-ba33-9c05203868e9',  'Elon Musk', 'spacex');

insert into chat (created_at, updated_at, uuid ) values ( '2022-07-15 14:52:49.065758', '2022-07-15 14:52:49.065813', '1c20fb12-60d8-4322-ba33-9c05203868e9');

insert into chat_accounts  (chat_id, account_id ) values (1 , 1);

insert into message (created_at, updated_at, uuid, text, chat_id, from_id ) values ( '2022-07-15 14:52:49.065758', '2022-07-15 14:52:49.065813', '2c20fb12-60d8-4322-ba33-9c05203868e9', 'hi, how are you?', 1, 2);


