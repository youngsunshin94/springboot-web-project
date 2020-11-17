
let replyService = (function(){

    function add(reply, callback) {
        $.ajax({
            url:'/replies/new',
            type: 'post',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(reply),
            success: function (result) {
               if(callback) {
                   callback(result);
               }
            }
        });
    }

    function getList(param, callback) {
        let bno = param.bno;
        let page = param.page || 1;

        $.getJSON("/replies/pages/" + bno + "/" + page, function(result){
            if(callback) {
                callback(result.list, result.replyCnt);
            }
        });
    }

    function getOne(rno, callback) {
        $.getJSON("/replies/" + rno , function(result){
            if (callback) {
                callback(result);
            }
        })
    }

    function update(reply, callback) {
        $.ajax({
           url:"/replies/" + reply.rno,
           type: "put",
           contentType: "application/json; charset=utf-8",
           data: JSON.stringify(reply),
           success: function (result) {
               if(callback) {
                   callback(result);
               }
           }
        });
    }

    function remove(rno, callback) {
        $.ajax({
           url:"/replies/" + rno,
           type: 'delete',
           contentType: "application/json; charset=utf-8",
           data: JSON.stringify(rno),
            success: function (result) {
               if(callback){
                   callback(result);
               }
            }
        });
    }

    function displayTime(timeValue) {
        var dateObj = new Date(timeValue);

        var yy = dateObj.getFullYear();
        var mm = dateObj.getMonth()+1;
        var dd = dateObj.getDate();

        return [yy,"/", (mm > 9 ? '' : '0') + mm,"/", (dd > 9 ? '' : '0')+dd].join('');
    }

    return {
        add:add,
        getList:getList,
        getOne:getOne,
        update:update,
        remove:remove,
        displayTime:displayTime
    }

})();