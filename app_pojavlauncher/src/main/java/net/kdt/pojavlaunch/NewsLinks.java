package net.kdt.pojavlaunch;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

/**
 * NOTE: fill in DISCORD / YOUTUBE / WEBSITE below with News Launcher's own channels.
 * Empty values make the matching buttons show a "not set up yet" message.
 *
 * Phase 8 — the launcher's community links in ONE place.
 *
 * <p>The About page, the News Client promo and now the home brand cluster all
 * point at the same channels; keeping the URLs here means one edit updates
 * every button. {@link #open} tries the native app first (the YouTube /
 * Discord app claims these URLs) and falls back to the browser.
 */
public final class NewsLinks {

    /** Same invite as {@code AboutFragment}. */
    public static final String DISCORD = "";
    /** Same channel as {@code CsClientPromoDialog}. */
    public static final String YOUTUBE = "";
    public static final String WEBSITE = "";
    public static final String GITHUB = "https://github.com/anjalifredy-ai/News-Launcher";

    private NewsLinks() {}

    public static void open(@NonNull Context ctx, @NonNull String url) {
        if (url == null || url.trim().isEmpty()) {
            Toast.makeText(ctx, "This link has not been set up yet", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(ctx, "No app can open this link", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(ctx, "Could not open link", Toast.LENGTH_SHORT).show();
        }
    }
}
