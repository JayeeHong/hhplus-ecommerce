import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 200, // 동시 200명
  duration: '30s', // 30초 지속 (편의상..)
};

export default function () {
  const userId = Math.floor(Math.random() * 1000000) + 1;
  const url = `http://host.docker.internal:8080/api/v1/users/${userId}/coupons/publish`;
  const payload = JSON.stringify({
    couponId: 1234,
  });
  const params = { headers: { 'Content-Type': 'application/json' } };

  const res = http.post(url, payload, params);
  sleep(Math.random()); // 0~1초 랜덤 슬립
}
