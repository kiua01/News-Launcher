# News Launcher

News Launcher is a Minecraft: Java Edition launcher for Android focused on performance,
customisation and a smooth user experience. It is based on **CS Launcher Plus** by Craft Studio
(used with the owner's permission), which itself builds on Amethyst and PojavLauncher.

## Highlights

- Redesigned cream "glass" home screen (hero + Play, installations, news, quick links) with a labelled sidebar
- Fully customisable on-screen controls (move, resize, custom button images, custom settings button)
- Multiple renderers, including **MobileGlues 2.0.0** (bundled for arm64-v8a, armeabi-v7a, x86, x86_64)
- Performance and stability improvements

## Build with GitHub Actions

1. Push this repository to GitHub (branch `main`).
2. Open **Actions -> Build News Launcher APK -> Run workflow**. The APK appears under *Artifacts*.
3. Push a tag like `v1.0.0` to also attach the APK to a GitHub Release.

### Optional repository secrets (Settings -> Secrets and variables -> Actions)

| Secret | Purpose |
|---|---|
| `KEYSTORE_BASE64` | Your release keystore, base64-encoded (`base64 -w0 my.keystore`) |
| `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD` | Passwords/alias for that keystore |
| `CURSEFORGE_API_KEY` | Your own CurseForge API key (CurseForge browsing is disabled without it) |
| `GOOGLE_SERVICES_JSON` | Your own Firebase `google-services.json`, base64-encoded (announcements / push / update prompts) |

Without a keystore secret the APK is signed with the debug key: fine for testing, but updates
only install over each other if you always sign with the **same** key, so create a release key
once and keep it safe.

## Things to set up before publishing

- `app_pojavlauncher/src/main/java/net/kdt/pojavlaunch/NewsLinks.java` - your Discord / YouTube / website links (empty = button shows "not set up yet"), and confirm the GitHub URL.
- `remote/config.json` and `launch/RemoteConfigManager.java` - remote loading-video config now points at `anjalifredy-ai/News-Launcher`; change the account name if your repository lives elsewhere.
- `res/values/firebase_strings.xml` - add your own Firebase Realtime Database URL to enable remote announcements.
- Launcher icon / logo - generated placeholders (white "N" on navy). Replace `res/mipmap-*` and `res/drawable-nodpi/cs_logo.png` with your artwork.

## License and credits

GPL-3.0 (see `LICENSE`). Attribution to Craft Studio, Amethyst, PojavLauncher and the other
projects this work builds on is in `NOTICE` and inside the app's About page. Minecraft is a
trademark of Mojang Synergies AB / Microsoft; News Launcher is not affiliated with either.
