import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicMainPageComponent } from './public-main-page.component';

describe('PublicMainPageComponent', () => {
  let component: PublicMainPageComponent;
  let fixture: ComponentFixture<PublicMainPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicMainPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicMainPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
