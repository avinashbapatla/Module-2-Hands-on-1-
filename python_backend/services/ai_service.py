from groq import Groq
from config import GROQ_API_KEY

client = Groq(
    api_key=GROQ_API_KEY
)

MODEL_NAME = "llama-3.3-70b-versatile"


def generate_ai_response(user_message):

    try:

        completion = client.chat.completions.create(

            model=MODEL_NAME,

            messages=[
                {
                    "role": "system",
                    "content":
                        "You are a professional AI assistant for a mobile chatbot application. Answer clearly and politely."
                },
                {
                    "role": "user",
                    "content": user_message
                }
            ],

            temperature=0.7,
            max_completion_tokens=1024,
            top_p=1,
            stream=False

        )

        return completion.choices[0].message.content

    except Exception as e:

        return f"Error : {e}"