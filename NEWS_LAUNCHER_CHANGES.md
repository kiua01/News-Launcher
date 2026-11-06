# What changed from CS Launcher Plus (nexus-home-redesign) to News Launcher

## Branding
- App name, strings (all locales), About page, notifications, crash reports, user-agents, logs: now "News Launcher".
- `applicationId`: `com.newslauncher.app` (provider authorities derive from it). The Java namespace stays
  `net.kdt.pojavlaunch` on purpose: the native libraries export JNI functions under that name.
- Placeholder icons / logo (white "N" on navy), `ic_launcher_background` colour, loading-console monogram ("NL"),
  default profile icon. Replace with your own artwork when ready.
- Internal identifiers (`Cs*` class names, `cs_*` resource names, `assets/components/cs_client`) are unchanged: they are
  invisible to users and renaming them without a compiler is risky.
- The bundled client mod jars in `assets/components/cs_client/` are still Craft Studio's compiled builds.
- Original copyright/attribution kept in `NOTICE` and the About legal text.

## New home screen (based on your mockup)
- Cream "glass" theme: top bar (brand, search, notifications, controls, account), hero card with the live skin
  and **Play**, installations carousel underneath, right column with What's New / Quick Links / Join the community.
- Sidebar dock now shows labels and has two new entries: **Skins** and **Market** (mods/modpacks).
- Search, Market, Skins, Controls, Website, Patch Notes, Discord, YouTube and Join tiles are wired.
- The Minecraft logo and artwork from the mockup are not used (Mojang trademark / AI art): the hero shows the
  selected profile's own background. Installations sit where "Featured News" is in the mockup because there is
  no news feed yet; "What's New" is static text (edit it in `fragment_launcher_home.xml`).
- Not rendered on a device here: expect some spacing tweaks. Send me a screenshot and I will adjust.

## MobileGlues 2.0.0
- `libmobileglues.so` replaced with the 2.0.0 build for arm64-v8a, armeabi-v7a, x86, x86_64 (previous source had 1.3.5
  for three ABIs and none for arm64-v8a). Taken from the CS Launcher Plus v1.5.1 APK; version macro reads 2000.
- Existing config.json keys (`enableANGLE`, `multidrawMode`, ...) are still read by 2.0.0, so the settings screen works as before.
- New: `MG_ANGLE_DIR` is exported so MobileGlues 2.x can borrow the launcher's bundled ANGLE.
- Also added from the same APK (missing from the source): arm64-v8a `libltw`, `libopenal`, `libunpack200`, ANGLE pair;
  armeabi-v7a `libltw`, `libopenal`, ANGLE pair.

## Removed / replaced owner-specific things
- Deleted `release.keystore` (with hard-coded passwords) and the committed `google-services.json` (a shared Firebase project).
- Release signing now comes from GitHub secrets; falls back to the debug key if none is set.
- Firebase: no default database (announcements/push/update prompts are off until you add your own). Sponsorship (Infrawire)
  is off by default; its text still names CS Launcher Plus because that partnership is not transferable.
- Links (Discord/YouTube/website) are empty placeholders in `NewsLinks.java`; remote config URLs point to `anjalifredy-ai/News-Launcher`.
- `.gitmodules` removed (the native build does not use the submodules).

## CI
- `.github/workflows/build-apk.yml`: JDK 17, SDK 34, NDK 27.3.13750724, optional secrets, uploads the APK artifact,
  attaches it to a Release on `v*` tags.

## Not verified
- No Gradle build was run (no network in the authoring environment) and nothing was tested on a device.
