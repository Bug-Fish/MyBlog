package blog.demo.controller;

import blog.demo.service.BlogService;
import blog.demo.service.TagService;
import blog.demo.service.TypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class IndexController {
    @Resource
    private BlogService blogService;
    @Resource
    private TagService tagService;
    @Resource
    private TypeService typeService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 4, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(3));
        model.addAttribute("tags", tagService.listTagTop(6));
        //model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));


        return "blog";
    }

    @GetMapping("/about")
    public String about() {

        return "about";
    }

    @GetMapping("/footer/newblog")
    private String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments::newblogList";
    }
}
