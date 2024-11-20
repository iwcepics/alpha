import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterStudentPostData, RegisterTeacherPostData, Student, Teacher } from '../interfaces/auth';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:3000';
  constructor(private http: HttpClient) {}

  registerStudent(postData: RegisterStudentPostData) {
    return this.http.post(`${this.baseUrl}/students`, postData);
  }

  getStudentDetails(id: number, password: string): Observable<Student[]> {
    return this.http.get<Student[]>(
      `${this.baseUrl}/students?id=${id}&password=${password}`
    );
  }

  registerTeacher(postData: RegisterTeacherPostData) {
    return this.http.post(`${this.baseUrl}/teachers`, postData);
  }

  getTeacherDetails(email: string, password: string): Observable<Teacher[]> {
    return this.http.get<Teacher[]>(
      `${this.baseUrl}/teachers?email=${email}&password=${password}`
    );
  }
}
