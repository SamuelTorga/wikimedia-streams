db.auth('root', 'secret');

db = db.getSiblingDB('wikimedia');

db.createCollection('recent_changes');

db.createUser({
    user: 'wikimedia_app_user',
    pwd: 'password',
    roles: [
        {role: "readWrite", db: "wikimedia"}
    ]
});

db.recent_changes.createIndex({type: 1, user: 1, domain: 1}, {name: 'idx_recent_changes'});