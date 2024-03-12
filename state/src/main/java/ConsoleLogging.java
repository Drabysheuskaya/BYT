public class ConsoleLogging implements Logging{
    @Override
    public void logMessage(Log log) {
        String text = log.toString();
        String colorCode;
        switch (log.getLevel().getIndex()) {
            case 1:
                colorCode = "\u001B[38;5;34m";
                break;
            case 2:
                colorCode = "\u001B[38;5;208m";
                break;
            case 3:
                colorCode = "\u001B[38;5;196m";
                break;
            default:
                colorCode = "\u001B[0m";
        }
        System.out.println(colorCode + text + "\u001B[0m");
        for (int i = 0; i < text.length(); i++){
            System.out.print("*");
        }
        System.out.println();
    }
}
