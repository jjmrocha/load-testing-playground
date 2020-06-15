"""Create application

Revision ID: 9120340a3b45
Revises: 
Create Date: 2020-06-15 00:47:17.601348

"""
import sqlalchemy as sa
from alembic import op

# revision identifiers, used by Alembic.
revision = '9120340a3b45'
down_revision = None
branch_labels = None
depends_on = None


def upgrade():
    op.create_table(
        'application',
        sa.Column("app_id", sa.Integer, primary_key=True),
        sa.Column("name", sa.String(80))
    )


def downgrade():
    op.drop_table('application')
