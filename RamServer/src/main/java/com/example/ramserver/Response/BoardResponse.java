package com.example.ramserver.Response;

import com.example.ramserver.vo.BoardViewVo;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BoardResponse {
    private String msg;
    private BoardViewVo post;
}
