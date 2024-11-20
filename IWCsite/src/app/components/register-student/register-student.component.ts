import { Component, inject } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { passwordMismatchValidator } from '../../shared/password-mismatch.directive';
import { AuthService } from '../../services/auth.service';
import { RegisterStudentPostData } from '../../interfaces/auth';
import { MessageService } from 'primeng/api';
import { MenuComponent } from '../menu/menu.component';
import { map } from 'rxjs';

@Component({
  selector: 'app-register-student',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CardModule,
    InputTextModule,
    PasswordModule,
    ButtonModule,
    RouterLink,
    MenuComponent,
  ],
  templateUrl: './register-student.component.html',
  styleUrl: './register-student.component.css',
})
export class RegisterStudentComponent {
  private registerStudentService = inject(AuthService);
  private messageService = inject(MessageService);
  private router = inject(Router);
  registerForm = new FormGroup(
    {
      fullName: new FormControl('', [Validators.required]),
      language: new FormControl('', [
        Validators.required,
        Validators.pattern(/[a-zA-Z]+/),
      ]),
      level: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('', [Validators.required]),
    },
    {
      validators: passwordMismatchValidator,
    }
  );

  onRegister() {
    const postData = { ...this.registerForm.value };
    delete postData.confirmPassword;
    this.registerStudentService.registerStudent(postData as RegisterStudentPostData).subscribe({
      next: (response) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Registered successfully',
        });
        this.router.navigate(['login-student']);
        console.log(response);
      },
      error: (err) => {
        console.log(err);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Something went wrong',
        });
      },
    });
  }

  get fullName() {
    return this.registerForm.controls['fullName'];
  }

  get language() {
    return this.registerForm.controls['language'];
  }

  get level() {
    return this.registerForm.controls['level'];
  }

  get password() {
    return this.registerForm.controls['password'];
  }

  get confirmPassword() {
    return this.registerForm.controls['confirmPassword'];
  }
}

