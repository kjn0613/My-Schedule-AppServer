package com.sparta.mycalendarappserver.dto;

import com.sparta.mycalendarappserver.entity.Schedule;
import lombok.Getter;

@Getter

public class ScheduleResponseDto
{
    private Long id;
    private String username;
    private String contents;

    public ScheduleResponseDto(Schedule schedule)
    {
        this.id = schedule.getId();
        this.username = schedule.getUsername();
        this.contents = schedule.getContents();
    }
}

