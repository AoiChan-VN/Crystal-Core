PRAGMA foreign_keys = ON;
PRAGMA journal_mode = WAL;

CREATE TABLE IF NOT EXISTS cultivators (
    uuid TEXT PRIMARY KEY,
    username TEXT NOT NULL,
    realm TEXT NOT NULL,
    stage INTEGER NOT NULL,
    cultivation_exp INTEGER NOT NULL,
    spirit_root TEXT NOT NULL,
    elements TEXT NOT NULL,
    spirit_energy REAL NOT NULL,
    spirit_energy_max REAL NOT NULL,
    combat_power INTEGER NOT NULL,
    meridians TEXT NOT NULL,
    sect_id TEXT,
    sect_rank TEXT,
    last_online INTEGER NOT NULL,
    created_at INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS cultivator_skills (
    player_uuid TEXT,
    skill_id TEXT,
    skill_level INTEGER,
    PRIMARY KEY(player_uuid, skill_id)
);

CREATE TABLE IF NOT EXISTS sects (
    id TEXT PRIMARY KEY,
    name TEXT UNIQUE,
    leader_uuid TEXT,
    level INTEGER,
    experience INTEGER,
    created_at INTEGER
);

CREATE TABLE IF NOT EXISTS sect_members (
    player_uuid TEXT PRIMARY KEY,
    sect_id TEXT,
    rank TEXT,
    joined_at INTEGER,
    contribution INTEGER
); 
