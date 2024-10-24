<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f1f1f1;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .chat-container {
            width: 400px;
            height: 600px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            background-color: #ffffff;
        }

        .chat-box {
            flex-grow: 1;
            padding: 20px;
            overflow-y: auto;
            border-bottom: 1px solid #ddd;
        }

        .chat-box .message {
            margin-bottom: 15px;
        }

        .chat-box .message.user {
            text-align: right;
        }

        .chat-box .message.user span {
            background-color: #0084ff;
            color: white;
            padding: 8px 12px;
            border-radius: 15px;
            display: inline-block;
            max-width: 70%;
        }

        .chat-box .message.bot span {
            background-color: #e0e0e0;
            color: black;
            padding: 8px 12px;
            border-radius: 15px;
            display: inline-block;
            max-width: 70%;
        }

        .chat-input-area {
            display: flex;
            padding: 10px;
            border-top: 1px solid #ddd;
        }

        #chat-input {
            flex: 1;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }

        #send-button {
            padding: 10px 15px;
            margin-left: 10px;
            background-color: #0084ff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        #send-button:hover {
            background-color: #006bbd;
        }
    </style>
</head>
<body>
    <div class="chat-container">
        <div class="chat-box" id="chat-box">
            <!-- 채팅 메시지가 여기에 추가됩니다 -->
        </div>
        <div class="chat-input-area">
            <input type="text" id="chat-input" placeholder="Type a message...">
            <button id="send-button">Send</button>
        </div>
    </div>

    <script>
        const chatBox = document.getElementById('chat-box');
        const chatInput = document.getElementById('chat-input');
        const sendButton = document.getElementById('send-button');

        // 메시지 전송 함수
        function sendMessage() {
            const message = chatInput.value;
            if (message.trim() === '') return;

            // 사용자 메시지를 채팅창에 추가
            appendMessage('user', message);
            chatInput.value = '';

            // 서버에 메시지 전송
            fetch('http://localhost:8080/chatbot', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ message: message }),
            })
            .then(response => response.text()) // 응답을 텍스트로 받음
            .then(data => {
                // 서버의 응답을 채팅창에 추가
                appendMessage('bot', data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }

        // 채팅 메시지를 추가하는 함수
        function appendMessage(sender, message) {
            const messageDiv = document.createElement('div');
            messageDiv.classList.add('message', sender);
            const messageSpan = document.createElement('span');
            messageSpan.innerHTML = message; // innerHTML로 응답을 처리
            messageDiv.appendChild(messageSpan);
            chatBox.appendChild(messageDiv);
            chatBox.scrollTop = chatBox.scrollHeight; // 스크롤을 맨 아래로 내림
        }

        // Send 버튼 클릭 시 메시지 전송
        sendButton.addEventListener('click', sendMessage);

        // 엔터 키로 메시지 전송
        chatInput.addEventListener('keydown', function(event) {
            if (event.key === 'Enter') {
                sendMessage();
            }
        });
    </script>
<%@ include file="layout/footer.jsp"%>