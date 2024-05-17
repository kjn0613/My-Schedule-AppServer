package com.sparta.mycalendarappserver.controller;

import com.sparta.mycalendarappserver.dto.ScheduleRequestDto;
import com.sparta.mycalendarappserver.dto.ScheduleResponseDto;
import com.sparta.mycalendarappserver.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api") //다수의 api가 사용될 예정이기 때문에 생성.

public class ScheduleController
{
    //바로 아래 프라이베이트 파이날은 데이터베이스 즉 데이터 저장소 대신 임시로 사용하는 것.
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    //처음에는 API테이블을 만든다. 바로 아래꺼.

    // 1.등록하기
    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto)

    //http바디에 제이슨 형태로 데이터가 넘어오려면 (@RequestBody)와 같이 작성된다.
    //(@RequestBody ScheduleRequestDto requestDto)와 같이 ( )의 구성을 파라미터라 부름.
    //이제 리퀘스트dto를 엔티티로 수정해야함. 저장해야하기 때문.

    {
        Schedule schedule = new Schedule(requestDto);

        //Schedule의 Max ID 체크. 중복예방.
        Long maxId = scheduleList.size() > 0 ? Collections.max(scheduleList.keySet()) + 1 : 1;
        schedule.setId(maxId);

        //DB저장.
        scheduleList.put(schedule.getId(), schedule);

        //Entity를 ResponseDto로 바꾸기.
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    // 2.등록하기
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules()
    {
        //Map To List
        List<ScheduleResponseDto> responseList = scheduleList.values().stream().map(ScheduleResponseDto::new).toList();

        return responseList;
    }

    // 3.업데이트
    @PutMapping("/schedules/{id}")
    public Long updateSschedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto)
    {
        //해당 일정이 DB에 존재하는지 체크.
        if(scheduleList.containsKey(id))
        {
            //해당 일정 가져오기.
            Schedule schedule = scheduleList.get(id);

            //가져온 일정 수정.
            schedule.update(requestDto);
            return schedule.getId();
        }

        else
        {
            throw new IllegalArgumentException("해당 일정은 존재하지 않습니다.");
        }
    }

    // 4.삭제하기
    @DeleteMapping("/schedules/{id}")
    public Long deleteSchedules(@PathVariable Long id)
    {
        //해당 일정이 DB에 존재하는지 체크.
        if(scheduleList.containsKey(id))
        {
            //해당 일정 삭제하기
            scheduleList.remove(id);
            return id;
        }

        else
        {
            throw new IllegalArgumentException("해당 일정은 존재하지 않습니다.");
        }
    }
}
