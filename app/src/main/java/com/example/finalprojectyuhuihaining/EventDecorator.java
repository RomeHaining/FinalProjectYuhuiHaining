package com.example.finalprojectyuhuihaining;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class EventDecorator implements DayViewDecorator {
    private final int[] colors;
    private final HashSet<CalendarDay> dates;

    public EventDecorator(int[] color, Collection<CalendarDay> dates) {
        this.colors = color;
        this.dates = new HashSet<>(dates);
    }

    /*public EventDecorator(List filteredEvents) {
        //this.dates = new HashSet<>(filteredEvents.get(0).calDayArr);
        int[] color = {0};
        //color[0]= filteredEvents.get(0).color;
        this.colors = color;
    }*/

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan((new CustmMultipleDotSpan(5, colors)));
    }
}
