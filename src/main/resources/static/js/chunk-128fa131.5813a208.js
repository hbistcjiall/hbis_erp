(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-128fa131"],{4257:function(t,s,e){},d070:function(t,s,e){"use strict";var i=e("4257");e.n(i).a},fdb7:function(t,s,e){"use strict";e.r(s);var i={name:"htjd_pz",data:function(){return{lists:[],CXQ:[],CXH:[],Csvj:{flName:""},CXQvalue:{flName:"",sort:"0"},CXHvalue:{flName:"",sort:"1"}}},created:function(){this.handleListApproveHistory()},methods:{handleListApproveHistory:function(){var t=this;fetch(this.$store.state.fetchPath+"/allocation/selAllocation",{method:"POST",headers:this.$store.state.fetchHeader,body:this.utils.formatParams(this.Csvj),credentials:"include"}).then(function(s){if(200==s.status)return s.text();t.$Message.error("请求失败！")}).then(function(s){s=0<s.length?JSON.parse(s):[],t.lists=t.utils.htjdTree(s)}),fetch(this.$store.state.fetchPath+"/allocation/selScheduleByCx",{method:"POST",headers:this.$store.state.fetchHeader,body:this.utils.formatParams(this.CXQvalue),credentials:"include"}).then(function(s){if(200==s.status)return s.text();t.$Message.error("请求失败！")}).then(function(s){s=0<s.length?JSON.parse(s):[],t.CXQ=t.utils.htjdTree(s)}),fetch(this.$store.state.fetchPath+"/allocation/selScheduleByCx",{method:"POST",headers:this.$store.state.fetchHeader,body:this.utils.formatParams(this.CXHvalue),credentials:"include"}).then(function(s){if(200==s.status)return s.text();t.$Message.error("请求失败！")}).then(function(s){s=0<s.length?JSON.parse(s):[],t.CXH=t.utils.htjdTree(s)})},getJt:function(){this.Csvj.flName="",this.CXQvalue.flName="",this.CXQvalue.sort="0",this.CXHvalue.flName="",this.CXHvalue.sort="1",this.handleListApproveHistory()},getXszgs:function(){this.Csvj.flName="3",this.CXQvalue.flName="3",this.CXQvalue.sort="0",this.CXHvalue.flName="3",this.CXHvalue.sort="1",this.handleListApproveHistory()},getZgs:function(){this.Csvj.flName="4",this.CXQvalue.flName="4",this.CXQvalue.sort="0",this.CXHvalue.flName="4",this.CXHvalue.sort="1",this.handleListApproveHistory()},getCk:function(){this.Csvj.flName="2",this.CXQvalue.flName="2",this.CXQvalue.sort="0",this.CXHvalue.flName="2",this.CXHvalue.sort="1",this.handleListApproveHistory()}}},a=(e("d070"),e("2877")),l=Object(a.a)(i,function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",[e("Row",{staticStyle:{"margin-bottom":"20px"}},[e("Button",{attrs:{type:"primary"},on:{click:t.getJt}},[t._v("集团")]),e("Button",{staticStyle:{"margin-left":"9px"},attrs:{type:"primary"},on:{click:t.getXszgs}},[t._v("销售总公司")]),e("Button",{staticStyle:{"margin-left":"9px"},attrs:{type:"primary"},on:{click:t.getZgs}},[t._v("子公司")]),e("Button",{staticStyle:{"margin-left":"9px"},attrs:{type:"primary"},on:{click:t.getCk}},[t._v("出口")])],1),e("div",{staticStyle:{width:"40%",float:"left","margin-top":"20px"}},[t._m(0),e("ul",t._l(t.lists,function(s){return e("li",{staticClass:"item-icon-right"},[e("div",{staticClass:"titleStyle"},[t._v(t._s(s.title))]),e("div",{staticClass:"progressContainer"},[s.wcbl<=.49?e("div",{staticClass:"progress",style:{width:100*s.wcbl+"%","background-color":"#ed4117"}},[e("b",[t._v("已产量:"+t._s(s.ycl)+"吨/"+t._s(100*s.wcbl)+"%")])]):.49<s.wcbl&&s.wcbl<=.79?e("div",{staticClass:"progress",style:{width:100*s.wcbl+"%","background-color":"#fa9909"}},[e("b",[t._v("已产量:"+t._s(s.ycl)+"吨/"+t._s(100*s.wcbl)+"%")])]):e("div",{staticClass:"progress",style:{width:100*s.wcbl+"%","background-color":"#2ebf6b"}},[e("b",[t._v("已产量:"+t._s(s.ycl)+"吨/"+t._s(100*s.wcbl)+"%")])])]),e("div",{staticClass:"progressContainer"},[e("div",{staticClass:"progress",style:{width:"100%","background-color":"#33b7f6","margin-top":"5px"}},[e("b",[t._v("计划量:"+t._s(s.jhl)+"吨/100%")])])])])}),0)]),e("div",{staticStyle:{float:"left",width:"20%","margin-left":"10%"}},[e("div",{staticStyle:{"font-size":"24px","font-weight":"bold"}},[t._v("产线前10")]),e("ul",t._l(t.CXQ,function(s){return e("li",{staticClass:"item-icon-right"},[e("div",{staticClass:"titleStyle"},[t._v(t._s(s.cxtitle)+",产线:"+t._s(s.ycl)+"万")]),e("div",{staticClass:"progressContainer"},[e("div",{staticClass:"progress",style:{width:100*s.wcbl+"%","background-color":"#3793cf"}},[e("b",[t._v("进度:"+t._s(100*s.wcbl)+"%")])])])])}),0)]),e("div",{staticStyle:{float:"right",width:"20%","margin-right":"5%"}},[e("div",{staticStyle:{"font-size":"24px","font-weight":"bold"}},[t._v("产线后10")]),e("ul",t._l(t.CXH,function(s){return e("li",{staticClass:"item-icon-right",staticStyle:{"font-size":"5px"}},[e("div",{staticClass:"titleStyle"},[t._v(t._s(s.cxtitle)+",产线:"+t._s(s.ycl)+"万")]),e("div",{staticClass:"progressContainer"},[e("div",{staticClass:"progress",style:{width:100*s.wcbl+"%","background-color":"#3793cf"}},[e("b",[t._v("进度:"+t._s(100*s.wcbl)+"%")])])])])}),0)])],1)},[function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("div",{staticStyle:{float:"right"}},[e("div",{staticClass:"CStyle"},[t._v("1%-49%"),e("span",{staticStyle:{background:"#ed4117"}})]),e("div",{staticClass:"CStyle"},[t._v("5%0-79%"),e("span",{staticStyle:{background:"#fa9909"}})]),e("div",{staticClass:"CStyle"},[t._v("80%-100%"),e("span",{staticStyle:{background:"#2ebf6b"}})])])}],!1,null,"a2807954",null);s.default=l.exports}}]);