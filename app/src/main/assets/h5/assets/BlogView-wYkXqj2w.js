import{d as j,r as I,o as E,c as n,a as t,t as a,F as f,b as C,e as p,w as r,n as U,f as l}from"./index-d9vlfaLW.js";import{c as k}from"./common-lLZLEzU9.js";const A={class:"blog",id:"blog"},D={class:"blog-title"},F={class:"blog-info"},O={class:"blog-author"},Q={class:"blog-time"},q={key:0,class:"blog-relative"},z={class:"blog-relative-subject"},G={class:"tip"},K=["onClick"],P=["src"],W={class:"title"},X=["innerHTML"],Y={key:1,class:"blog-tag"},Z=t("div",{class:"tip"},"标签：",-1),tt={class:"blog-tag-item"},et={key:2,class:"divider"},st={key:3,class:"blog-comment"},ot={class:"blog-comment-title"},nt=t("div",{class:"title"},"精选留言",-1),lt=t("div",{style:{flex:"1"}},null,-1),it={class:"blog-comment-item"},ct=["src","onClick"],at={class:"comment-content"},dt=["onClick"],rt=["onClick"],_t={class:"time"},vt=["innerHTML","onClick"],ut=t("div",{style:{height:"12px"}},null,-1),gt={class:"blog-comment-item"},ht=["src","onClick"],pt=["onClick"],kt={class:"info"},wt=["onClick"],yt={class:"time"},ft=["innerHTML"],Ct={key:4,class:"blog-space"},Rt=j({__name:"BlogView",setup(bt){const s=I(),N=I(),J={loadBlogDetail:async i=>{s.value=i,await U();const d=N.value;k.injectImageClick(d)}},x=i=>{const d=(i||"").trim(),c="　　",_=c+d.replace(/&nbsp;/g," ").trim().replace(/(\r\n|\n|\r)\s+/g,"$1").replace(/(\r\n|\n|\r)/g,`$1${c}`);return console.log(_),_},b=(i,d,c)=>{const _=i.target;if(k.injectHandleItemClick(_))return;k.scrollIntoView(i,document.getElementById("blog"),c==null);const v=(c==null?void 0:c.replyJs)||"",y=d.replyJs||"",g=v.length>0,h=g?v:y,u=c||d;if(window.android){if(h.length>0){g&&(u.replyQuote=k.handleQuote(u.userName,u.replyContent)),window.android.onReplyUser(v.length>0?v:h,JSON.stringify(u));return}window.android.onNeedLogin()}},S=i=>{k.scrollIntoView(i,document.getElementById("blog"),!0),window.android&&window.android.onReplyNew()},V=i=>{window.android&&window.android.onClickRelated(JSON.stringify(i))},w=i=>{window.android&&window.android.onClickUser(i.userId||"")};return E(()=>{window.blog=J,window.mounted=!0}),(i,d)=>{var c,_,v,y,g,h,u,R,M,H,L,T,$,B;return l(),n("div",A,[t("div",D,a((c=s.value)==null?void 0:c.title),1),t("div",F,[t("div",O,a((_=s.value)==null?void 0:_.userName),1),t("div",Q,a((v=s.value)==null?void 0:v.time),1)]),(((y=s.value)==null?void 0:y.related)||[]).length>0?(l(),n("div",q,[t("div",z,[t("div",G,"关联的条目 "+a((h=(g=s.value)==null?void 0:g.related)==null?void 0:h.length)+" 个",1),(l(!0),n(f,null,C(((u=s.value)==null?void 0:u.related)||[],e=>(l(),n("div",{class:"relative",onClick:r(o=>V(e),["stop"])},[t("img",{src:e.cover,alt:"img"},null,8,P),t("div",W,"# "+a(e.titleNative),1)],8,K))),256))])])):p("",!0),t("div",{class:"blog-content",ref_key:"blogContentRef",ref:N,innerHTML:x((R=s.value)==null?void 0:R.content)},null,8,X),(((M=s.value)==null?void 0:M.tags)||[]).length>0?(l(),n("div",Y,[Z,(l(!0),n(f,null,C(((H=s.value)==null?void 0:H.tags)||[],e=>(l(),n("div",tt,a(e.title),1))),256))])):p("",!0),(L=s.value)!=null&&L.content?(l(),n("hr",et)):p("",!0),(T=s.value)!=null&&T.content?(l(),n("div",st,[t("div",ot,[nt,lt,t("div",{class:"write",onClick:d[0]||(d[0]=r(e=>S(e),["stop"]))},"写留言")]),(l(!0),n(f,null,C((($=s.value)==null?void 0:$.comments)||[],e=>(l(),n("div",it,[t("img",{class:"avatar",src:e.userAvatar,alt:"img",onClick:r(o=>w(e),["stop"])},null,8,ct),t("div",at,[t("div",{class:"info",onClick:r(o=>b(o,e,null),["stop"])},[t("div",{class:"user-name",onClick:r(o=>w(e),["stop"])},a(e.userName),9,rt),t("div",_t,a(e.time),1)],8,dt),t("div",{class:"blog-html",innerHTML:e.replyContent,onClick:r(o=>b(o,e,null),["stop"])},null,8,vt),ut,(l(!0),n(f,null,C(e.topicSubReply||[],o=>(l(),n("div",gt,[t("img",{class:"avatar sub",src:o.userAvatar,alt:"img",onClick:r(m=>w(o),["stop"])},null,8,ht),t("div",{class:"comment-content",onClick:r(m=>b(m,e,o),["stop"])},[t("div",kt,[t("div",{class:"user-name",onClick:r(m=>w(o),["stop"])},a(o.userName),9,wt),t("div",yt,a(o.time),1)]),t("div",{class:"blog-html",innerHTML:o.replyContent},null,8,ft)],8,pt)]))),256))])]))),256))])):p("",!0),(B=s.value)!=null&&B.content?(l(),n("div",Ct," 我是有底线的 ")):p("",!0)])}}});export{Rt as default};