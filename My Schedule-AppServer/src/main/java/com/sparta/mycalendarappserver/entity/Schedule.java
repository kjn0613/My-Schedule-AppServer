package com.sparta.mycalendarappserver.entity;

//엔티티는 데이터베이스와 소통할 때 사용하는 패키지.

import com.sparta.mycalendarappserver.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Schedule
{
    //필드 선언.
    private Long id; //일정끼리 구분하기 위해.
    private String username; //일정을 작성한 사람의 이름.
    private String contents; //일정의 내용.

    public Schedule(ScheduleRequestDto requestDto)
    {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        //리퀘스트 dto에서, 겟 메서드를 사용해 유저의 이름과,
        // 컨텐스(내용물)을 가지고 와서 스케줄 클래스 안에 있는 두 필드에 데이터를 넣어주면서,
        // 스케줄 객체를 하나 만들어내는 생성자를 만든 것.
    }

    public void update(ScheduleRequestDto requestDto)
    {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
