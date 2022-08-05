import * as functions from "firebase-functions";
import * as admin from "firebase-admin";

admin.initializeApp({
    credential: admin.credential.applicationDefault(),
});

exports.pushConfig = functions.remoteConfig.onUpdate(async metadata => {
    const payload = {
        data: {
            CONFIG_STATE: 'STALE'
        },
        topic: 'REMOTE_CONFIG'
    };

    // Use the Admin SDK to send the ping via FCM.
    await admin.messaging().send(payload)
});
