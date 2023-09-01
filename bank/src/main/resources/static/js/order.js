

console.log("order axios방식 처리")


// 주문 버튼 클릭시
// 형식: await axios.post('url', 전송할 데이터 객체, { headers: { 데이터형식}} )
/* 
  ex)
  await axios.post(
    '/order',               // url
    {키:"값",...} or 객체,  // 데이터
    { headers: { 
        'Content-Type': "multipart/form-data" or 
                        "application/json;charset=utf-8" ,  // 데이터형식
        [_csrf_header]: _csrf                               // 토큰 전송
    }})

*/

// 주문 버튼 클릭시 
// async function orderItem(orderItemData){

//     const response = await axios.post('/order', orderItemData)

//     console.log("요청 응답 결과:"+response.data)

//     //return response.data;
//     return response;
// }


// // 주문취소 버튼 클릭시
// async function cancelOrderId(orderIdData){

//     const response = await axios.post('/order/'+orderIdData.orderId+'/cancel', orderIdData)

//     // return response.data; 
//     return response;
// }



// 장바구니 버튼 클릭시 
// async function cartItem(orderItemData){

//     const response = await axios.post('/cart', orderItemData)

//     console.log("요청 응답 결과:"+response.data)

//     //return response.data;
//     return response;
// }