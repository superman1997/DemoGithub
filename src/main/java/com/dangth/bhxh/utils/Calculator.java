package com.dangth.bhxh.utils;

import com.dangth.bhxh.model.Worker;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Calculator {
    static final double MAX_SAL = 27800000;
    static final double MIN_SAL_VOLUNTEER = 700000;
    public static final int NORMAL_TYPE = 1;
    public static final int VOLUNTEER_TYPE = 2;
    public static double calculate(Worker worker) {
        double fee = 0;
        log.info("Calculate fee : ");
        if (worker.getType() == NORMAL_TYPE) {
            log.info("TYPE : Bat buoc");
            int zone = worker.getZone();
            double sal = worker.getSalary() + worker.getPc();
            log.info("SAL : " + String.format("%.0f", sal));
            if (zone == 1 && sal >= 4180000) {
                fee = sal * 0.08;
            } else if (zone == 2 && sal >= 3710000) {
                fee = sal * 0.08;
            } else if (zone == 3 && sal >= 3250000) {
                fee = sal * 0.08;
            } else if (zone == 4 && sal >= 2920000) {
                fee = sal * 0.08;
            } else fee = 0;

            if (sal >= MAX_SAL) fee = MAX_SAL * 0.08;
            log.info("FEE : " + fee);
            return fee;

        }
        else if (worker.getType() == VOLUNTEER_TYPE) {
            log.info("TYPE : Tu nguyen");
            double sal = worker.getSalary() + worker.getPc();
            log.info("SAL : " + String.format("%.0f", sal));
            fee = sal * 0.22;
            if (sal < MIN_SAL_VOLUNTEER) return 0;
            if (sal > MAX_SAL) fee = MAX_SAL * 0.22;
            log.info("FEE : " + fee);
            return fee;

        } else {
            log.error("TYPE : zero");
            return 0;
        }
    }
}
