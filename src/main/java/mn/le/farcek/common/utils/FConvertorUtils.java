/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.le.farcek.common.utils;

/**
 *
 * @author Farcek
 */
public class FConvertorUtils {

    public static Duration second2Duration(int seconds) {
        int h, m, s;

        h = seconds / 3600;
        m = (seconds - h * 3600) / 60;
        s = (seconds - (h * 3600 + m * 60));

        return new Duration(h, m, s);
    }

    public static class Duration {

        private int hours;
        private int minutes;
        private int seconds;

        public Duration() {
        }

        public Duration(int hours, int minutes, int seconds) {
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }
    }
}
