CREATE TABLE bookmarks
(
    id         BIGINT                 NOT NULL,
    updated_at datetime DEFAULT NOW() NOT NULL,
    title      VARCHAR(200)           NOT NULL,
    url        VARCHAR(500)           NOT NULL,
    created_at datetime DEFAULT NOW() NOT NULL,
    CONSTRAINT pk_bookmarks PRIMARY KEY (id)
);