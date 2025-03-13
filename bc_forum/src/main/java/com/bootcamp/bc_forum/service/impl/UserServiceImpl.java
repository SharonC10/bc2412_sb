// package com.bootcamp.bc_forum.service.impl;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;
// import org.springframework.web.util.UriComponentsBuilder;
// import com.bootcamp.bc_forum.dto.CommentDTO;
// import com.bootcamp.bc_forum.dto.PostDTO;
// import com.bootcamp.bc_forum.dto.UserDTO;
// import com.bootcamp.bc_forum.dto.CommentDTO.Comment;
// import com.bootcamp.bc_forum.model.dto.NewCommentDto;
// import com.bootcamp.bc_forum.model.dto.PostDto;
// import com.bootcamp.bc_forum.model.dto.UserDto;
// import com.bootcamp.bc_forum.service.CommentService;
// import com.bootcamp.bc_forum.service.PostService;
// import com.bootcamp.bc_forum.service.UserService;

// @Service
// public class UserServiceImpl implements UserService {
//   @Autowired
//   private RestTemplate restTemplate;
//   @Autowired
//   private PostService postService;
//   @Autowired
//   private CommentService commentService;

//   @Value("${api.jsonplaceholder.domain}")
//   private String domain;

//   @Value("${api.jsonplaceholder.endpoints.users}")
//   private String usersEndpoint;

//   @Override
//   public List<UserDTO> getUsers() {
//     String url = UriComponentsBuilder.newInstance().scheme("https").host(domain)
//         .path(usersEndpoint).build().toUriString();

//     System.out.println("url" + url);
//     List<UserDto> userDtos =
//         Arrays.asList(this.restTemplate.getForObject(url, UserDto[].class));

//     List<UserDTO> userList = new ArrayList<>();

//     for (UserDto userDto : userDtos) {
//       UserDTO userDTO = new UserDTO(//
//           userDto.getId(), //
//           userDto.getName(), //
//           userDto.getUsername(), //
//           userDto.getEmail(), //
//           new UserDTO.Address(//
//               userDto.getAddress().getStreet(), //
//               userDto.getAddress().getSuite(), //
//               userDto.getAddress().getCity(), //
//               userDto.getAddress().getZipcode(), //
//               new UserDTO.Address.Geo(//
//                   userDto.getAddress().getGeo().getLat(), //
//                   userDto.getAddress().getGeo().getLng()//
//               )//
//           ), //
//           userDto.getPhone(), //
//           userDto.getWebsite(), //
//           new UserDTO.Company(//
//               userDto.getCompany().getName(), //
//               userDto.getCompany().getCatchPhrase(), //
//               userDto.getCompany().getBs()), //
//           postService.getPostDtos(userDto.getId()));//

//       userList.add(userDTO);

//     }
//     return userList;
//   }

//   // user id == post user_id -> show post
//   @Override
//   public List<CommentDTO> getCommentById(Long userId) {
//     String url2 = UriComponentsBuilder.newInstance().scheme("https")
//         .host(domain).path(usersEndpoint).build().toUriString();

//     List<UserDto> userDtos2 =
//         Arrays.asList(this.restTemplate.getForObject(url2, UserDto[].class));
//     // another url

//     List<PostDTO> postById = new ArrayList<>(); // new array (empty now, need to fill up)

//     for (UserDto dto2 : userDtos2) {
//       if (dto2.getId() == userId) {
//         PostDTO userPost = new PostDTO( // fill up
//             dto2.getId(), dto2.getUsername(),
//             postService.getPostDtos(dto2.getId()));
//             postById.add(userPost);
//       }
//     };

// List<CommentDTO> commentById = new ArrayList<>();

//     for(PostDTO postDTO : postById){
//       //if(postDTO.getId() == userId){
//       CommentDTO userComment = new CommentDTO(
//         postDTO.getId(),
//         postDTO.getUsername(),
//         commentService.getCommentDtos(postDTO.getId())
//         .stream()
//         .map(e -> new CommentDTO.Comment(
//           e.getName(),
//           e.getEmail(),
//           e.getBody()
//         )).toList());


//         commentById.add(userComment);
//     }
//   //}
//     return commentById;

//   }

// }
