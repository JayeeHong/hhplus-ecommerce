import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 5000, // 동시 5,000명
  duration: '10s', // 10초간 순간 집중
};

export default function () {
  const userId = Math.floor(Math.random() * 1000000) + 1;
  const url = `http://host.docker.internal:8080/api/v1/users/${userId}/coupons/publish`;
  const payload = JSON.stringify({
    couponId: 1234,
  });
  const params = { headers: { 'Content-Type': 'application/json' } };

  const res = http.post(url, payload, params);
}
