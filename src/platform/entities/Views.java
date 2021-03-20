package platform.entities;

public class Views {
    public static class OnlyIdView {
    }

    public static class OnlyDateView {
    }

    public static class CodeInfoAndDateView extends OnlyDateView {
    }

    public static class PublicView extends CodeInfoAndDateView {
    }
}
