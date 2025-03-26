package Ewallet;

import Ewallet.service.impl.ApplicationServiceImpl;

public class Main {
    public static void main(String[] args) {

        ApplicationServiceImpl applicationService = new ApplicationServiceImpl();
        applicationService.start();

        }
}