package com.li.petit.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "url_mapping")
@Data
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_url", nullable = false)
    private String shortUrl;

    // 如果没有getter方法，你可以添加一个获取 longUrl 的方法
    @Getter
    @Column(name = "long_url", nullable = false) // 使用正确的列名
    private String longUrl;

    @Column(name = "username", nullable = false)
    private String username;

}




