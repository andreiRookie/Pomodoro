
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


class Main {


    /*
    -w 1 -b 1 -count 1
    split()
    [-w, 1, -b, 1, -count, 1]
     */

    public static void main(String[] args) throws InterruptedException {
        // Пользовательский ввод
        printHelpMsg();
        System.out.println("Hello Pomodoro. Напишите, пожалуйста, команду:");


        String[] input = new Scanner(System.in).nextLine().split(" ");

//            время работы
        int workTimeMin = 1;
        // время отдыха
        int breakTimeMin = 1;
        // кол-во подходов
        int count = 1;
        // длина рисунка progress bar
        int sizePrint = 30;

            boolean isCallHelp = false;

            for (int i = 0; i < input.length; i++) {
                switch (input[i]) {
                    case "--help" -> {
                        printHelpMsg();
                        isCallHelp = true;
                    }
                    case "-w" -> workTimeMin = Math.abs(Integer.parseInt(input[++i]));
                    case "-b" -> breakTimeMin = Math.abs(Integer.parseInt(input[++i]));
                    case "-c" -> count = Math.abs(Integer.parseInt(input[++i]));
                    case "-t" -> isTest = true;
                    case "--exit" -> { System.out.println("Чао!");
                        return;}
                }
            }

            if (!isCallHelp) {
                System.out.printf("Работаем %d min, " +
                        "отдывахем %d min, кол-во подходов %d\n", workTimeMin, breakTimeMin, count);
                long startTime = System.currentTimeMillis();
                for (int i = 1; i <= count; i++) {
                    timer(workTimeMin, breakTimeMin, sizePrint);
                }
                long endTime = System.currentTimeMillis();
                System.out.println("Pomodoro таймер истек: " + (endTime - startTime)/(1000 * 60) + " min");
            }
    }

   private static void timer(
           int workTimeMin,
           int breakTimeMin,
           int size) throws InterruptedException {

        printProgress("Время поработать:: ", workTimeMin, size);

        printProgress("Время отдыхать:: ", breakTimeMin, size);
   }

    static boolean isTest = false;

    //метод прогрессБара
    private static void printProgress(String process, int time, int size) throws InterruptedException {

//        String pattern = "##0.00";
//        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        int length;
        int rep;
        length = 60* time / size;
        rep = 60* time /length;
        int stretchb = size /(3* time);
        for(int i=1; i <= rep; i++){
            double x = i;
            x = 1.0/3.0 *x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time *stretchb;
            double percent = (x/w) *1000;
            x /=stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;

//            String formattedddDouble = String.format("%.2f%n", percent);

            System.out.print(process + percent +"% " + (" ").repeat(Math.abs(5 - (String.valueOf(percent).length()))) +"[" + ("#").repeat(i) + ("-").repeat(rep - i)+"]    ( " + x +"min / " + time +"min )"+  "\r");
            if(!isTest){
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
    }

    private static void printHelpMsg() {
        System.out.println("""
                    \nPomodoro - сделай свое время более эффективным
                    -w <time>: время работы, сколько хочешь работать.
                    -b <time>: время отдыха, сколько хочешь отдыхать.
                    -c <count>: количество итераций.
                    --help: меню помощи.
                    --exit: выход\n""");
    }
}