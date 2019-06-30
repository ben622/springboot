layui.use(['layer', 'form', 'element', 'jquery'], function() {
    var element = layui.element;
    var mainLayout = $('#main-layout');
    element.on("nav(menu)",function(elem){
        var nav_a = $(elem[0]);

        var id = nav_a.attr('data-id');
        var url = nav_a.attr('data-url');
        var text = nav_a.attr('data-text');

        if(!url) {
            return;
        }
        var isActive = $('.main-layout-tab .layui-tab-title').find("li[lay-id=" + id + "]");
        if(isActive.length > 0) {
            //切换到选项卡
            element.tabChange('tab', id);
        } else {
            element.tabAdd('tab', {
                title: text,
                content: '<iframe src="' + url + '" id="' + id + '" name="iframe' + id + '" class="iframe" frameborder="0" data-id="' + id + '" scrolling="auto" height="100%" width="100%"></iframe>',
                id: id
            });
            element.tabChange('tab', id);
        }
        mainLayout.removeClass('hide-side');
    })
});