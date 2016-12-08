$(function () {

  var $form = $("#form-edit");
  var $submitBtn = $(".btn-submit");
  var edit = {

    /** 初始化函数 */
    init: function () {
      this.validateForm();
      this.addValidateMethods();
    },

    addValidateMethods: function () {
      // 个数必须为正整数
      jQuery.validator.addMethod("countRequired", function (value, element) {
        var reg2 = new RegExp("^([1-9][0-9]*)$");
        if (reg2.test(value)) {
          return this.optional(element) || value > 0;
        }
      }, "必须为正整数");

      jQuery.validator.addMethod("aboveDenomination", function (value, element) {
        var denomination = $("#denomination").val();
        return parseFloat(value) >parseFloat( denomination);
      }, "限制金额应当大于优惠面额");
    },

    /** 验证表单字段 */
    validateForm: function () {
      var validator = $form.validate({
        ignore: "",
        rules: {
          title: {
            required: true
          },

          descpt: {
            required: true
          },
          expireTime: {
            required: true,
            date: true
          },

          denomination: {
            required: true,
            number: true
          },
          restrictionMoney: {
            required: true,
            number: true,
            aboveDenomination: true
          }
        },
        messages: {
          title: {
            required: "请输入标题",
          },

          descpt: {
            required: "请输入描述",
          },
          expireTime: {
            required: "请输入过期时间",
            date: "输入的时间格式不正确"
          },

          denomination: {
            required: "请输入优惠券面额",
            digits: "请输入正确金额"
          },
          restrictionMoney: {
            required: "请输入限制金额",
            digits: "请输入正确金额",
          }
        },

        submitHandler: function () {
          var buttonObj = this.submitButton;
          var memberId = $("#memberId").val();
          $(buttonObj).button("loading");
          $.ajax({
            url: $form.attr("action"),
            type: "POST",
            data: $form.serialize(),
            dataType: "JSON",
            success: function (data) {
              if (data.code == 0) {
                Dialog.success(data.msg, function () {
                  if ($(buttonObj).attr("id") == "saveAgain") {
                    location.href = ctx + "/member/coupon/add/" + memberId;
                  } else {
                    location.href = ctx + "/member/coupon/" + memberId;
                  }
                }, 1500);
              } else {
                validator.showErrors(data.errors);
                $submitBtn.button("reset");
                Dialog.danger(data.msg);
              }
            }
          });

        }
      })
    }
  };

  edit.init();

});