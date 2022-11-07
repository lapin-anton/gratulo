ALTER TABLE account
  RENAME COLUMN tg_tag TO tg_id;

ALTER TABLE account
  DROP COLUMN tg_group;