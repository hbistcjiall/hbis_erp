(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-3a7910e6"],{b9ec:function(t,e,i){},c98c:function(t,e,i){"use strict";var n=i("b9ec");i.n(n).a},d873:function(t,e,i){"use strict";i.r(e);var n={name:"bjsc",data:function(){return{switchTime:!0,startTime:new Date,cx:"",columns:[{title:"日期",align:"center",width:100,isMergeRow:!0,key:"ORDERDAY",fixed:"left"},{title:"单位",align:"center",width:85,key:"COMPANY"},{title:" ",align:"center",width:85,key:""},{title:"2019年以来建材北京市场现货数量、出库价汇总",align:"center",children:[{title:"高线（HPB300）",align:"center",children:[{title:"φ6.5mm",align:"center",width:85,key:"GXDATA1"},{title:"φ8mm",align:"center",width:85,key:"GXDATA2"},{title:"φ10mm",align:"center",width:85,key:"GXDATA3"},{title:"φ12mm",align:"center",width:85,key:"GXDATA4"}]},{title:"盘螺（HRB400/400E）",align:"center",children:[{title:"φ6mm",align:"center",width:85,key:"PLDATA1"},{title:"φ8mm",align:"center",width:85,key:"PLDATA2"},{title:"φ10mm",align:"center",width:85,key:"PLDATA3"},{title:"φ12mm",align:"center",width:85,key:"PLDATA4"}]},{title:"盘螺（HRB500/500E）",align:"center",children:[{title:"φ6mm",align:"center",width:85,key:"PLDATA5"},{title:"φ8mm",align:"center",width:85,key:"PLDATA6"},{title:"φ10mm",align:"center",width:85,key:"PLDATA7"},{title:"φ12mm",align:"center",width:85,key:"PLDATA8"}]},{title:"螺纹钢（HRB400/400E）",align:"center",children:[{title:"φ10mm",align:"center",width:85,key:"LWDATA1"},{title:"φ12mm",align:"center",width:85,key:"LWDATA2"},{title:"φ14mm",align:"center",width:85,key:"LWDATA3"},{title:"φ16mm",align:"center",width:85,key:"LWDATA4"},{title:"φ18mm",align:"center",width:85,key:"LWDATA5"},{title:"φ20mm",align:"center",width:85,key:"LWDATA6"},{title:"φ22mm",align:"center",width:85,key:"LWDATA7"},{title:"φ25mm",align:"center",width:85,key:"LWDATA8"},{title:"φ28mm",align:"center",width:85,key:"LWDATA9"},{title:"φ32mm",align:"center",width:85,key:"LWDATA10"},{title:"φ36mm",align:"center",width:85,key:"LWDATA11"},{title:"φ40mm",align:"center",width:85,key:"LWDATA12"},{title:"φ18-25mm",align:"center",width:100,key:"LWDATA13"}]},{title:"螺纹钢（HRB500/500E）",align:"center",children:[{title:"φ10mm",align:"center",width:85,key:"LWDATA14"},{title:"φ12mm",align:"center",width:85,key:"LWDATA15"},{title:"φ14mm",align:"center",width:85,key:"LWDATA16"},{title:"φ16mm",align:"center",width:85,key:"LWDATA17"},{title:"φ18mm",align:"center",width:85,key:"LWDATA18"},{title:"φ20mm",align:"center",width:85,key:"LWDATA19"},{title:"φ22mm",align:"center",width:85,key:"LWDATA20"},{title:"φ25mm",align:"center",width:85,key:"LWDATA21"},{title:"φ28mm",align:"center",width:85,key:"LWDATA22"},{title:"φ32mm",align:"center",width:85,key:"LWDATA23"},{title:"φ36mm",align:"center",width:85,key:"LWDATA24"},{title:"φ40mm",align:"center",width:85,key:"LWDATA25"},{title:"φ18-25mm",align:"center",width:100,key:"LWDATA26"}]}]}],data:[]}},mounted:function(){this.getList()},methods:{getList:function(){var t=this,e={};this.cx&&(e.company=this.cx);var i="endMonth=";this.switchTime?i=(i+this.utils.formatdate(this.startTime)).substring(0,20):i+=this.utils.formatYearStart(this.year),fetch(this.$store.state.fetchPath+"/reportSpotPriceBreakdown/list",{method:"POST",headers:this.$store.state.fetchHeader,body:i+"&"+this.utils.formatParams(e),credentials:"include"}).then(function(e){if(200==e.status)return e.text();t.$Message.error("请求失败！")}).then(function(e){e=e&&0<e.length?JSON.parse(e):[],t.data=e.dayList,t.data=t.utils.mergeRow(e.dayList,"ORDERDAY")})},downLoad:function(){this.$refs.table.exportCsv({filename:"北京市场明细"})}}},l=(i("c98c"),i("2877")),a=Object(l.a)(n,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("Form",{attrs:{"label-width":100}},[i("Row",[t.switchTime?i("Col",{attrs:{span:"4"}},[i("FormItem",{staticStyle:{width:"150px"},attrs:{label:"记录日期："}},[i("DatePicker",{staticStyle:{width:"150px"},attrs:{type:"date",placeholder:"记录时间",editable:!1,clearable:!1},model:{value:t.startTime,callback:function(e){t.startTime=e},expression:"startTime"}})],1)],1):t._e(),i("Col",{attrs:{span:"4"}},[i("FormItem",{staticStyle:{width:"120px","margin-left":"50px"},attrs:{label:"钢厂："}},[i("Select",{staticStyle:{width:"120px"},attrs:{placeholder:"请选择"},model:{value:t.cx,callback:function(e){t.cx=e},expression:"cx"}},[i("Option",{attrs:{value:"1"}},[t._v("宣钢")]),i("Option",{attrs:{value:"2"}},[t._v("承钢")])],1)],1)],1),i("Col",{staticStyle:{"margin-left":"80px"},attrs:{span:"4"}},[i("Button",{attrs:{icon:"ios-search"},on:{click:function(e){return t.getList()}}},[t._v("查询")]),i("Button",{attrs:{icon:"ios-cloud-download-outline",type:"primary"},on:{click:function(e){return t.downLoad()}}},[t._v("导出")])],1)],1)],1),i("Table",{ref:"table",attrs:{columns:t.columns,data:t.data,border:"",height:"500"}})],1)},[],!1,null,"7b3bd263",null);e.default=a.exports}}]);