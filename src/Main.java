public class Main {
    public static void main(String[] args) {
        String s = "asdngoasodokdasddasfsdgsdfsadfasdasdadadasdasdasdasd1hyqdg";
        System.out.println(decode(s));
    }

    private static String decode(String node) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < node.length(); i++) {
            char current_char = node.charAt(i);
            if (sb.indexOf(String.valueOf(current_char)) == -1) {
                sb.append(current_char);
            }
        }
        return sb.toString();
    }
}
