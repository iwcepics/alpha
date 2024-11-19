import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

import { User } from '../interfaces/user';
import { Teacher } from '../interfaces/teacher';
import { Student } from '../interfaces/student';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  login(user: User): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, user);
  }

  registerTeacher(teacher: Teacher) {
    return this.http.post(`${this.apiUrl}/register/teacher`, teacher);
  }

  registerStudent(student: Student) {
    return this.http.post(`${this.apiUrl}/register/student`, student);
  }

  getAllTeachers() {
    return this.http.get(`${this.apiUrl}/teachers/all`);
  }
}
