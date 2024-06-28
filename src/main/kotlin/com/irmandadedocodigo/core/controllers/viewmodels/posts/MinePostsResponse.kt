package com.irmandadedocodigo.core.controllers.viewmodels.posts

data class MinePostsResponse(
    var content: String,
    var id: Long,
    var author: Author
){
    data class Author(
        var id: String,
        var name: String
    ){}
}
