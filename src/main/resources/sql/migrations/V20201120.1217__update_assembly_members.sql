-- Capitalize Khaleel Anderson's short name

UPDATE public.session_member
SET lbdc_short_name = 'ANDERSON'
WHERE id = 1638;

-- Insert CLARK
WITH p AS (
  INSERT into public.person(full_name, first_name, middle_name, last_name, email, prefix, suffix, img_name)
    VALUES ('Sarah Clark', 'Sarah', '', 'Clark', '', 'Assembly Member', '', 'no_image.jpg')
    RETURNING id
),
m AS (
  INSERT INTO public.member(person_id, chamber, incumbent, full_name)
    VALUES ((SELECT id from p), 'assembly', true, 'Sarah Clark')
    RETURNING id
)

INSERT INTO public.session_member(member_id, lbdc_short_name, session_year, district_code)
  VALUES ((SELECT id from m), 'CLARK', 2019, 136);


-- Insert BROWN
WITH p AS (
  INSERT into public.person(full_name, first_name, middle_name, last_name, email, prefix, suffix, img_name)
    VALUES ('Keith Brown', 'Keith', '', 'Brown', '', 'Assembly Member', '', 'no_image.jpg')
    RETURNING id
),
m AS (
  INSERT INTO public.member(person_id, chamber, incumbent, full_name)
    VALUES ((SELECT id from p), 'assembly', true, 'Keith Brown')
    RETURNING id
)

INSERT INTO public.session_member(member_id, lbdc_short_name, session_year, district_code)
  VALUES ((SELECT id from m), 'BROWN', 2019, 12);
