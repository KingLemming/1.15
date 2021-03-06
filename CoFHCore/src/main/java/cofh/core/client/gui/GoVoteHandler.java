//package cofh.core.client.gui;
//
//import cofh.core.CoFHCore;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
//import net.minecraft.client.gui.screen.MultiplayerScreen;
//import net.minecraft.client.gui.screen.Screen;
//import net.minecraft.client.gui.screen.WorldSelectionScreen;
//import net.minecraft.util.Util;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.StringTextComponent;
//import net.minecraft.util.text.TextFormatting;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.GuiOpenEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
//import org.lwjgl.glfw.GLFW;
//
//import javax.annotation.Nonnull;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.lang.reflect.Type;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.FileAlreadyExistsException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.*;
//
//import static cofh.core.util.constants.Constants.ID_COFH_CORE;
//
//@EventBusSubscriber(value = Dist.CLIENT, modid = ID_COFH_CORE)
//public class GoVoteHandler {
//
//    private static final String BRAND = "Team CoFH";
//    private static final String MARKER_PATH = ".vote2020_marker";
//    private static final LocalDate ELECTION_DAY = LocalDate.of(2020, Month.NOVEMBER, 3);
//    private static final String LINK = "https://vote.gov/";
//    private static boolean shownThisSession = false;
//
//    private static volatile boolean markerAlreadyExists = false;
//    private static volatile String countryCode = Locale.getDefault().getCountry();
//
//    public static void init() {
//
//        if (isAfterElectionDay()) {
//            return;
//        }
//        try {
//            Path path = Paths.get(MARKER_PATH);
//            Files.createFile(path);
//            if (Util.getOSType() == Util.OS.WINDOWS) {
//                Files.setAttribute(path, "dos:hidden", true);
//            }
//        } catch (FileAlreadyExistsException ex) {
//            CoFHCore.LOG.debug("Go vote handler: Marker already exists");
//            markerAlreadyExists = true;
//            return;
//        } catch (IOException ignored) {
//
//        }
//        new Thread(() -> {
//            try {
//                URL url = new URL("http://ip-api.com/json/");
//                URLConnection conn = url.openConnection();
//                conn.setConnectTimeout(4000);
//                conn.setReadTimeout(4000);
//                try (InputStreamReader reader = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)) {
//                    Type typeToken = new TypeToken<Map<String, String>>() {}.getType();
//                    Map<String, String> map = new Gson().fromJson(reader, typeToken);
//                    countryCode = map.get("countryCode");
//                }
//            } catch (IOException ignored) {
//            }
//        }, "Go Vote Country Check").start();
//    }
//
//    private static boolean isAfterElectionDay() {
//
//        return LocalDate.now().isAfter(ELECTION_DAY);
//    }
//
//    @SubscribeEvent
//    public static void clientTick(GuiOpenEvent event) {
//
//        Minecraft mc = Minecraft.getInstance();
//        Screen curr = event.getGui();
//        if ((curr instanceof WorldSelectionScreen || curr instanceof MultiplayerScreen) && shouldShow(mc)) {
//            event.setGui(new GoVoteScreen(curr));
//            shownThisSession = true;
//        }
//    }
//
//    private static boolean shouldShow(Minecraft mc) {
//
//        if (!isEnglish(mc) || shownThisSession || isAfterElectionDay() || markerAlreadyExists) {
//            return false;
//        }
//
//        return "US".equals(countryCode);
//    }
//
//    private static boolean isEnglish(Minecraft mc) {
//
//        return mc.getLanguageManager() != null
//                && mc.getLanguageManager().getCurrentLanguage() != null
//                && "English".equals(mc.getLanguageManager().getCurrentLanguage().getName());
//    }
//
//    private static class GoVoteScreen extends Screen {
//
//        private static final int TICKS_PER_GROUP = 50;
//        private final Screen parent;
//        private int ticksElapsed = 0;
//        private final List<List<ITextComponent>> message = new ArrayList<>();
//
//        protected GoVoteScreen(Screen parent) {
//
//            super(new StringTextComponent(""));
//            this.parent = parent;
//            addGroup(s("Please read the following message from " + BRAND + "."));
//            addGroup(s("We are at a unique crossroads in the history of our country."));
//            addGroup(s("In this time of heightened polarization,"),
//                    s("breakdown of political decorum, and fear,"));
//            addGroup(s("it is tempting to succumb to apathy,"),
//                    s("to think that nothing you do will matter."));
//            addGroup(s("But power is still in the hands of We, the People."));
//            addGroup(s("The Constitution and its amendments guarantee every citizen the right to vote."));
//            addGroup(s("And it is not only our right, but our ")
//                    .appendSibling(s("responsibility").applyTextStyles(TextFormatting.ITALIC, TextFormatting.GOLD))
//                    .appendText(" to do so."));
//            addGroup(s("Your vote matters. Always."));
//            addGroup(s("Click anywhere to check if you are registered to vote."),
//                    s("The website is an official government site, unaffiliated with " + BRAND + "."));
//            addGroup(s("Press ESC to exit. (This screen will not show up again.)"));
//        }
//
//        // Each group appears at the same time
//        private void addGroup(ITextComponent... lines) {
//
//            message.add(Arrays.asList(lines));
//        }
//
//        private static StringTextComponent s(String txt) {
//
//            return new StringTextComponent(txt);
//        }
//
//        @Override
//        public void tick() {
//
//            super.tick();
//            ticksElapsed++;
//        }
//
//        @Override
//        public void render(int mx, int my, float pticks) {
//
//            super.render(mx, my, pticks);
//
//            fill(0, 0, width, height, 0xFF696969);
//            int middle = width / 2;
//            int dist = 12;
//
//            ITextComponent note1 = s("Note: If you can't vote in the United States,").applyTextStyle(TextFormatting.ITALIC);
//            ITextComponent note2 = s("Please press ESC and carry on.").applyTextStyle(TextFormatting.ITALIC);
//            drawCenteredString(font, note1.getString(), middle, 10, 0xFFFFFF);
//            drawCenteredString(font, note2.getString(), middle, 22, 0xFFFFFF);
//
//            int y = 46;
//            for (int groupIdx = 0; groupIdx < message.size(); groupIdx++) {
//                List<ITextComponent> group = message.get(groupIdx);
//                if ((ticksElapsed - 20) > groupIdx * TICKS_PER_GROUP) {
//                    for (ITextComponent line : group) {
//                        drawCenteredString(font, line.getString(), middle, y, 0xFFFFFF);
//                        y += dist;
//                    }
//                }
//            }
//        }
//
//        @Nonnull
//        @Override
//        public String getNarrationMessage() {
//
//            StringBuilder builder = new StringBuilder();
//            for (List<ITextComponent> group : message) {
//                for (ITextComponent line : group) {
//                    builder.append(line.getString());
//                }
//            }
//            return builder.toString();
//        }
//
//        @Override
//        public boolean keyPressed(int keycode, int scanCode, int modifiers) {
//
//            if (keycode == GLFW.GLFW_KEY_ESCAPE) {
//                minecraft.displayGuiScreen(parent);
//            }
//            return super.keyPressed(keycode, scanCode, modifiers);
//        }
//
//        @Override
//        public boolean mouseClicked(double x, double y, int modifiers) {
//
//            if (ticksElapsed < 80) {
//                return false;
//            }
//            if (modifiers == 0) {
//                minecraft.displayGuiScreen(new ConfirmOpenLinkScreen(this::consume, LINK, true));
//                return true;
//            }
//            return super.mouseClicked(x, y, modifiers);
//        }
//
//        private void consume(boolean doIt) {
//
//            minecraft.displayGuiScreen(this);
//            if (doIt) {
//                Util.getOSType().openURI(LINK);
//            }
//        }
//
//    }
//
//}
