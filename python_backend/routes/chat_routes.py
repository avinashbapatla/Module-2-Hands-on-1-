from flask import Blueprint, request, jsonify
from chatbot import generate_reply

chat_bp = Blueprint("chat", __name__)

@chat_bp.route("/chat", methods=["POST"])
def chat():

    data = request.get_json()

    print("Received JSON:", data)

    if not data:
        return jsonify({"reply": "Invalid Request"}), 400

    message = data.get("message", "")

    print("Message:", message)

    reply = generate_reply(message)

    return jsonify({
        "reply": reply
    })