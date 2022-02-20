package com.tistory.workshop6349.workshoptodo.service;

import com.tistory.workshop6349.workshoptodo.domain.dto.PostDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostModifyDto;
import com.tistory.workshop6349.workshoptodo.domain.dto.PostResponseDto;
import com.tistory.workshop6349.workshoptodo.domain.entity.Member;
import com.tistory.workshop6349.workshoptodo.domain.entity.Post;
import com.tistory.workshop6349.workshoptodo.domain.repository.MemberRepository;
import com.tistory.workshop6349.workshoptodo.domain.repository.PostRepository;
import com.tistory.workshop6349.workshoptodo.advice.exception.AlreadyPostExistedException;
import com.tistory.workshop6349.workshoptodo.advice.exception.MemberNotFoundException;
import com.tistory.workshop6349.workshoptodo.advice.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /*
    글 작성
     */
    @Transactional
    public void createTodo(PostDto postDto) {
        Member member = memberRepository.findByEmail(postDto.getWriterEmail())
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾지 못하였습니다."));

        // 해당 글이 이미 존재하는 지 여부 묻기 -> 해당 작성자에게는 동일한 제목인 todo는 존재해서는 안됨
        if (postRepository.existsByMemberIdAndTitle(member.getId(), postDto.getTitle())) {
            throw new AlreadyPostExistedException("이미 해당 제목으로 작성된 todo 리스트가 있습니다.");
        }

        Post post = postDto.toEntity();
        post.setMember(member);

        postRepository.save(post);
    }

    @Transactional
    public void modifyPost(PostModifyDto postModifyDto) {
        Member member = memberRepository.findByEmail(postModifyDto.getMemberEmail())
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾지 못하였습니다."));

        // 해당 글의 작성자가 본인인지 파악
        Post post = postRepository.findByMemberIdAndTitle(member.getId(), postModifyDto.getOriginTitle())
                .orElseThrow(() -> new PostNotFoundException("글을 찾지 못하였습니다."));

        if (postModifyDto.getNewTitle() != null && !postModifyDto.getNewTitle().isBlank()) {
            // 제목 수정
            post.setTitle(postModifyDto.getNewTitle());
        }

        if (postModifyDto.getContent() != null && !postModifyDto.getContent().isBlank()) {
            // 내용 수정
            post.setContent(postModifyDto.getContent());
        }

        new PostResponseDto(postRepository.save(post));
    }

    /*
    글 체크
     */
    @Transactional
    public void checkPost(String email, long id, String title) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾지 못하였습니다."));

        // 해당 글의 작성자가 본인인지 파악
        Post post = postRepository.findByMemberIdAndTitle(id, title)
                .orElseThrow(() -> new PostNotFoundException("글을 찾지 못하였습니다."));

        post.setChecked(true);
        post.setCheckedDate(LocalDateTime.now());

        new PostResponseDto(postRepository.save(post));
    }

    /*
    글 삭제
     */
    @Transactional
    public void deletePost(String email, long id, String title) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾지 못하였습니다."));

        // 해당 글의 작성자가 본인인지 파악
        Post post = postRepository.findByMemberIdAndTitle(id, title)
                .orElseThrow(() -> new PostNotFoundException("글을 찾지 못하였습니다."));

        member.removePost(post);

        postRepository.delete(post);
    }

    /*
    글 찾기: 회원 ID, 글 제목
     */
    @Transactional(readOnly = true)
    public PostResponseDto findByMemberIdAndTitle(long id, String title) {
        Post post = postRepository.findByMemberIdAndTitle(id, title)
                .orElseThrow(() -> new PostNotFoundException("해당 작성자에게는 해당 제목인 글이 없습니다."));
        return new PostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAllByMemberId(long id) {
        List<Post> posts = postRepository.findAllByMemberId(id)
                .orElseThrow(() -> new PostNotFoundException("해당 작성자에게는 글이 존재하지 않습니다"));
        return posts.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAllByMemberEmailAndTitleContains(String email, String title) {
        List<Post> posts = postRepository.findAllByMemberEmailAndTitleContaining(email, title)
                .orElseThrow(() -> new PostNotFoundException("해당 작성자에게는 해당 제목이 있는 글이 존재하지 않습니다."));
        return posts.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

}
