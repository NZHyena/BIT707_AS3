package main;
/*
 * Copyright (C) 2022 Grant Docherty
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * BIT707 – Software Engineering 2021-2022
 * Written for the Graduate Diploma in Information Technology at the Open Polytechnic.
 * This is the work of a student and follows the principles of academic integrity set by the Open Polytech
 */

import java.time.LocalDate;

/**
 *
 * @author Grant Docherty - 5032768
 */
public class BIT707_AS3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Task day1Week1 = new Task();
        Task day3Week2 = new Task();

        Task lastDayWeek52 = new Task();

        day1Week1.setDate(LocalDate.parse("2022-01-01"));
        day3Week2.setDate(LocalDate.parse("2022-01-03"));
        lastDayWeek52.setDate(LocalDate.parse("2021-12-31"));
        
        Task today = new Task();
        today.setDate(LocalDate.now());

        for (int p : today.getCalendarPos()) {
            System.out.println(p);
        }
    }
    
}
