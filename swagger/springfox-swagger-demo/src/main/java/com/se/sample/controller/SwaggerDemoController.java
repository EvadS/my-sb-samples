package com.se.sample.controller;


import com.se.sample.models.*;
import com.se.sample.models.enums.SearchGroup;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
@Api(value = "spring-fox-swagger", description = "Operations to describe swagger models ")
public class SwaggerDemoController {

    private static final String EXAMPLE_UPDATE_TAGS = "\t[\n" +
            "example: List [ \"500-0000.0001.0001\", \"500-0000.0003.0001\" ]\n" +
            "500-0000.0001.0001: База податкових знань,\n" +
            "\n" +
            "500-0000.0002.0001: Судова практика,\n" +
            "\n" +
            "500-0000.0002.0005: Практика ЄСПЛ,\n";

    @ApiOperation(value = "test base api")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Successfully retrieved triggered api"),
    })
    @ResponseBody
    @RequestMapping(value = "/demo", method = RequestMethod.GET, produces = "application/json")
    public void demo() {

    }

    @ApiOperation(value = "get only search group")
    @ResponseBody
    @RequestMapping(value = "/demo2", method = RequestMethod.GET)
    public SearchGroup demo2() {
        return SearchGroup.E;
    }


    @ApiOperation(value = "searchGroup as a models field | API Operation annotation.")
    @ResponseBody
    @RequestMapping(value = "/demo3", method = RequestMethod.GET)
    public CustomDTO demo3() {
        return new CustomDTO();
    }

    @ApiOperation(value = "searchGroup with json serializer | API Operation annotation.")
    @ResponseBody
    @RequestMapping(value = "/demo4", method = RequestMethod.GET)
    public CustomDTO2 demo4() {
        return new CustomDTO2();
    }

    @ApiOperation(value = "searchGroup as String with  line break options | API Operation annotation.")
    @ResponseBody
    @RequestMapping(value = "/demo5", method = RequestMethod.GET)
    public CustomDTO3 demo5() {
        return new CustomDTO3();
    }

    @ApiOperation(value = "searchGroup as String with  line break options + const | API Operation annotation.")
    @ResponseBody
    @RequestMapping(value = "/demo6", method = RequestMethod.GET)
    public CustomDTO4 demo6() {
        return new CustomDTO4();
    }

    @ApiOperation(value = "api model property for enum value | API Operation annotation.")
    @ResponseBody
    @RequestMapping(value = "/demo7", method = RequestMethod.GET)
    public CustomDTO5 demo7() {
        return new CustomDTO5();
    }

    @ApiOperation(value = "api model property  array of string  | API Operation annotation.")
    @ResponseBody
    @RequestMapping(value = "/demo8", method = RequestMethod.GET)
    public MetRequest demo8() {
        return new MetRequest();
    }


    @ApiOperation(value = "list in model  | API Operation annotation.")
    @ResponseBody
    @RequestMapping(value = "/demo9", method = RequestMethod.POST)
    public String demo9(
            @ApiParam(
                    value = "<table>" +
                            "<tr>" +
                            "<td style=\"width: 30%\">1</td>" +
                            "<td>2</td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td style=\"width: 30%\">1</td>" +
                            "   <td>2</td>" +
                            "</tr>" +
                            "</table>") @RequestBody(required = true) CustomDTO6 request) {
        System.out.println("handle request");
        return "new CustomDTO6()";
    }


    @RequestMapping(value = "/demo10", method = RequestMethod.POST)
    public String demo10(
            @ApiParam(value = "tag to update，must have _id", required = true,
                    examples = @Example({@ExampleProperty(mediaType = "application/json", value = EXAMPLE_UPDATE_TAGS)}))
            @RequestBody(required = true) CustomDTO6 request) {
        System.out.println("handle request");
        return "new CustomDTO6()";
    }


    @ApiOperation(value = "list in model with table example | API Operation annotation.")
    @ResponseBody
    @RequestMapping(value = "/demo11", method = RequestMethod.POST)
    public String demo11(
            @ApiParam(
                    value = "<table>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0001.0001</td><td> База податкових знань</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0002.0001</td><td> Судова практика</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0002.0005</td><td> Практика ЄСПЛ</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0000</td><td> Міжнародне законодавство</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0001</td><td> Загальне законодавство</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0002</td><td> Регіональне законодавство</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0003</td><td> Столичне законодавство</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0005</td><td> Європейське законодавство</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0006</td><td> Внесені до ЄДРНПА</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0009.0001</td><td> Мистецтво оборони</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0009.0003</td><td> Алгоритми дій</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0011.0001</td><td> Інструкції та шаблони для кадровика</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0012.0000</td><td> Ситуації для бізнесу</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0012.0001</td><td> Ситуації для бухгалтера</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0012.0002</td><td> Ситуації для юриста</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0012.0003</td><td> Ситуації для кадровика</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0012.0004</td><td> Ситуації для комплаєнса</td></tr>\n" +
                            "</table>\n") @RequestBody(required = true) CustomDTO6 request) {
        System.out.println("handle request");
        return "new CustomDTO6()";
    }


    @ApiOperation(value = "list in model with table example | API Operation annotation.")
    @ResponseBody
    @RequestMapping(value = "/demo12", method = RequestMethod.GET)
    public String demo12(
            @ApiParam(value = "<table>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0001.0001</td><td> База податкових знань</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0002.0001</td><td> Судова практика</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0002.0005</td><td> Практика ЄСПЛ</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0000</td><td> Міжнародне законодавство</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0001</td><td> Загальне законодавство</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0002</td><td> Регіональне законодавство</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0003</td><td> Столичне законодавство</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0005</td><td> Європейське законодавство</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0003.0006</td><td> Внесені до ЄДРНПА</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0009.0001</td><td> Мистецтво оборони</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0009.0003</td><td> Алгоритми дій</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0011.0001</td><td> Інструкції та шаблони для кадровика</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0012.0000</td><td> Ситуації для бізнесу</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0012.0001</td><td> Ситуації для бухгалтера</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0012.0002</td><td> Ситуації для юриста</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0012.0003</td><td> Ситуації для кадровика</td></tr>\n" +
                            "<tr><td style=\"width: 30%\">500-0000.0012.0004</td><td> Ситуації для комплаєнса</td></tr>\n" +
                            "</table>\n") int aa) {
        System.out.println("handle request");
        return "new CustomDTO6()";
    }
}
