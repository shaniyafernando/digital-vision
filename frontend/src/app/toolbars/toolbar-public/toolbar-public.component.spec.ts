import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToolbarPublicComponent } from './toolbar-public.component';

describe('ToolbarPublicComponent', () => {
  let component: ToolbarPublicComponent;
  let fixture: ComponentFixture<ToolbarPublicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ToolbarPublicComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ToolbarPublicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
