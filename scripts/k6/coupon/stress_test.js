import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 500 },    // 30초에 500명까지 증가
    { duration: '30s', target: 2000 },   // 30초에 2,000명까지 증가
    { duration: '30s', target: 5000 },   // 30초에 5,000명까지 증가
    { duration: '30s', target: 10000 },  // 30초에 10,000명까지 증가
    { duration: '1m', target: 0 },       // 1분간 점차 감소
  ],
};

export default function () {
  const userId = Math.floor(Math.random() * 1000000) + 1;
  const url = `http://host.docker.internal:8080/api/v1/users/${userId}/coupons/publish`;
  const payload = JSON.stringify({
    couponId: 1234,
  });
  const params = { headers: { 'Content-Type': 'application/json' } };

  const res = http.post(url, payload, params);
  sleep(0.1);
}
