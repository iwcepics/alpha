export interface RegisterTeacherPostData {
  fullName: string;
  email: string;
  password: string;
}

export interface Teacher extends RegisterTeacherPostData {
  id: number;
}

export interface RegisterStudentPostData {
  fullName: string;
  language: string;
  level: string;
  password: string;
}

export interface Student extends RegisterStudentPostData {
  id: number;
}
