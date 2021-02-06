# Chat - Firebase Schema

```json
{
  "connections": {
    "${userId}": {
      "lastConnectionDate": null // Timestamp
    }
  },
  "userChatSessions": {
    "${userId}": {
      "userId": "${userId}",
      "userName": "${userName}",
      "hasBeenRead": false,
      "unreadRoomCount": 0,
      "sessions": {
        "${userId}": {
          "updatedDate": null,
          "userId": "${userId}",
          "userName": "${userName}",
          "chat": {
            "type": null, // TEXT or IMAGE
            "text": {
              "body": null
            },
            "image": {
              "link": null,
              "caption": null,
              "targetUrl": null
            }
          },
          "hasBeenRead": false
        }
      }
    }
  },
  "chatRooms": {
    "${userId}_${userId}": { // Identifier key - sorted by user id
      "users": {
        "${userId1}": {
          "name": "${userName1}"
        },
        "${userId2}": {
          "name": "${userName2}"
        }
      },
      "chats": {
        "${chatId}": {
          "createdDate": null,
          "userId": null,
          "hasBeenRead": false,
          "type": null,
          "text": {
            "body": null
          },
          "image": {
            "link": null,
            "caption": null,
            "targetUrl": null
          }
        }
      }
    }
  }
}
```
