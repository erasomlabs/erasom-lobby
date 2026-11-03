package erasom.lobby

data class SocialNetwork(
    val id: String,
    val label: String,
    val icon: String,
    val url: String
)

object SocialData {
    val networks = listOf(
        SocialNetwork("S1", "Instagram", "📸", "https://instagram.com"),
        SocialNetwork("S2", "Facebook", "📘", "https://facebook.com"),
        SocialNetwork("S3", "Messenger", "💬", "https://messenger.com"),
        SocialNetwork("S4", "X", "𝕏", "https://x.com"),
        SocialNetwork("S5", "YouTube", "▶️", "https://youtube.com"),
        SocialNetwork("S6", "Telegram", "✈️", "https://telegram.org"),
        SocialNetwork("S7", "TikTok", "🎵", "https://tiktok.com"),
        SocialNetwork("S8", "Reddit", "🔴", "https://reddit.com"),
        SocialNetwork("S9", "GitHub", "🐙", "https://github.com"),
        SocialNetwork("S10", "Threads", "🧵", "https://threads.net"),
        SocialNetwork("S11", "Discord", "🎮", "https://discord.com"),
        SocialNetwork("S12", "LinkedIn", "💼", "https://linkedin.com"),
        SocialNetwork("S13", "Snapchat", "👻", "https://snapchat.com"),
        SocialNetwork("S14", "WhatsApp", "📱", "https://whatsapp.com"),
        SocialNetwork("S15", "Web", "🌐", "https://erasomlabs.com")
    )
}
