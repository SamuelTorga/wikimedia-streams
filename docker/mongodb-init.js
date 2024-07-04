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