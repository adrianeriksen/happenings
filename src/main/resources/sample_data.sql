INSERT INTO `Users` (`email`, `hashed_password`, `name`, `created_at`, `updated_at`) VALUES (
    'adrian@example.io',
    '$argon2i$v=19$m=64,t=1,p=1$ZEZha2tTQUxaczJrdnZtVA$BxZz+I3v8LPDVRFRG3SMlQ',
    'Adrian E.',
    NOW(),
    NOW()
);

INSERT INTO `Users` (`email`, `hashed_password`, `name`, `created_at`, `updated_at`) VALUES (
    'jonas@example.io',
    '$argon2i$v=19$m=64,t=1,p=1$VUtMU0ZreEpMSzhibnp5Rg$CXLliJpG6a6AyywMp9Ox5Q',
    'Jonas S.',
    NOW(),
    NOW()
);

INSERT INTO `Events` (`title`, `where`, `description`, `starts_at`, `ends_at`, `created_at`, `updated_at`) VALUES (
    'Meet and greet with your cat',
    'Oslo, Norway',
    'Dr. Floof need quality petz frum frenz ^^,',
    '2020-01-10 16:30:00',
    '2020-01-10 21:00:00',
    NOW(),
    NOW()
);

INSERT INTO `EventResponses` (`user_id`, `event_id`, `status`, `created_at`, `updated_at`) VALUES (
    1,
    1,
    'HOST',
    NOW(),
    NOW()
);

INSERT INTO `EventResponses` (`user_id`, `event_id`, `status`, `created_at`, `updated_at`) VALUES (
    2,
    1,
    'INVITED',
    NOW(),
    NOW()
);
